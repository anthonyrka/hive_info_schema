package com.ryankirkish.hive.info;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.pig.impl.streaming.PigStreamingUDF;
import org.apache.hadoop.hive.metastore.api.MetaException;
import java.io.PrintWriter;
import java.io.File;
import java.util.List;

public class HiveInfoSchemas {

    public static void main(String[] args) throws Exception {
        
       
        PrintWriter writer = new PrintWriter("schema.txt","UTF-8");    
        HiveConf conf = new HiveConf();
        HiveMetaStoreClient client = new HiveMetaStoreClient(conf);
        List<String> databases = client.getAllDatabases();
        for (String db : databases) {
           
        List<String> tables = client.getAllTables(db);
        for (String tb : tables){
        try {

        List<FieldSchema> fields = client.getFields(db,tb);
        for (FieldSchema field : fields) {
            
            writer.println(db + "|" + tb+ "|" + field.getName() + "|" + field.getType());
            }   
        } catch (MetaException e){
                System.err.println("Caught MetaException: " + e.getMessage());
            }
        }
            }       


    writer.close();
    }

}
