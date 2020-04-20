import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class redis_demo {

	public static void main(String[] args) {
		try {
			Jedis jedis= new Jedis("localhost");
			 System.out.println("Connection Succesful");
			 System.out.println("Server Ping"+jedis.ping());
			
			 
			 System.out.println("\n"+"\n"); 
			 //Using SET and GET for key-value
			 System.out.println("Using SET and GET for key-value"); 
			  jedis.set("Name1", "Ms Dhoni"); 
		      System.out.println("Stored string in redis:: "+ jedis.get("Name1")); 
		      
		      System.out.println("\n"+"\n"); 
		    	//Expire
		     System.out.println("EXPIRE");  
		     jedis.set("Name3","BUMRAH");
		     jedis.expire("Name3", 50);
		     System.out.println("time to Expire for Name3 is::"+jedis.pttl("Name3"));
		     
			
		      System.out.println("\n"+"\n"); 
		    	//LIST
		     System.out.println("LIST"); 
		      jedis.lpush("Name2", "Virat Kohli"); 
		      jedis.lpush("Name2", "Sachin Tendulkar"); 
		      jedis.lpush("Name2", "Rohit Sharma"); 
		      // Get the stored data and print it 
		      List<String> list = jedis.lrange("Name2", 0 ,2); 
		      
		      for(int i = 0; i<list.size(); i++) { 
		         System.out.println("Stored string in redis:: "+list.get(i)); 
		      } 
		      
		      System.out.println("\n"+"\n"); 
		      //HASH 
		      System.out.println("HASH"); 
		      Map<String,String> map = new HashMap<String, String>();
		      map.put("Name1","Ms Dhoni");
		      map.put("age","33");
		      map.put("team","India");
		      jedis.hmset("hash-1", map);
		      System.out.println("HAsh-1 redis::"+jedis.hgetAll("hash-1"));
		      
		      
		      System.out.println("\n"+"\n"); 
		      //SET
		      System.out.println("SET"); 
		      jedis.sadd("set-1", "Shikhar", "Manish", "Yuvraj", "Jadeja","Jadeja");
		      System.out.println("Set-1 redis::"+jedis.smembers("set-1"));
		      
		      System.out.println("\n"+"\n"); 
		      //SORTED-SET
		      System.out.println("SORTED-SET"); 
		      jedis.zadd("sorted-set1", 1,"Ms Dhoni");
		      jedis.zadd("sorted-set1",2,"Sachin");
		      jedis.zadd("sorted-set1",3,"Virat");
		      System.out.println("ZRANGE\n"); 
		      System.out.println("sorted-set1 range 1-3 redis::"+jedis.zrange("sorted-set1",0,2));
		      System.out.println("\nZRANK\n"); 
		      System.out.println("sorted-set1 rank MS Dhoni redis::"+jedis.zrank("sorted-set1","Ms Dhoni"));
		      
		      
		      
		      
		    
		    
		}catch(Exception e) {
			System.out.println(e);
		}
		JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
		try  (Jedis jedis = jedisPool.getResource()) {
			   jedis.set("name1", "Abhisek");
			   jedis.set("name2", "Abhichal");
			   System.out.println(jedis.get("name1"));
			   System.out.println(jedis.get("name1"));
			}
		finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedisPool)
                jedisPool.close();
        }
	    
	     


	}

}
