package hi.mr;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class BillingTotalSort extends Configured implements Tool
{
    public static void main(String[] args) throws Exception
    {
        int res = ToolRunner.run(new Configuration(), new BillingTotal(), args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception
    {
    	
    	
    	
        if (args.length != 2)
        {
            System.out.println("usage : need <input path>  <output path>");
            return 1;
        }
        
        
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        Configuration conf = getConf();
        
      //Initialize new abstract Hadoop FileSystem
        FileSystem fs = FileSystem.get(conf);
        
       
          //Check if file doesn't exist
          if (fs.exists(outputPath)) {
            // if file exist, remove file first
            fs.delete(outputPath);
          }
       

        Job job = new Job(conf, getClass().getName() + "syedbahm"); // TODO
        job.setJarByClass(BillingTotal.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        TextInputFormat.setInputPaths(job, inputPath);
        TextOutputFormat.setOutputPath(job, outputPath);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    static class MyMapper extends Mapper<Object, Text, Text, Text>
    {


        @Override
        public void map(Object key, Text record, Context context) throws IOException
        {
//            System.out.println (record);
            try
            {
                String [] tokens = record.toString().split(",");
//                System.out.println (Arrays.toString(tokens));

                String timestampStr = tokens[0].trim();
                String customerIdStr = tokens[1].trim();
                String costStr = tokens[4].trim();
                int cost = Integer.parseInt(costStr);

                // TODO
                Text keyOutCustomer = new Text (customerIdStr);
                //IntWritable valueOutCost = new IntWritable(cost);
                context.write(keyOutCustomer, record);

            } catch (Exception e)
            {
                System.out.println("*** exception:");
                e.printStackTrace();
            }
        }

    }

    public static class MyReducer extends Reducer<Text, Text, NullWritable, Text>
    {

        public void reduce(Text key, Iterable<Text> results, Context context) throws IOException,
                InterruptedException
        {
            int total = 0;
            for (Text record : results)
            {
                // TODO
                // add up all the costs
            	//total += cost.get();
            
            context.write(NullWritable.get(), record);
            }

        }

    }

}
