package cleanup;

import java.io.File;

public class CleanUp {

	public boolean deleteOldDownLoadedFiles() {
		
		try{

    		File file = new File(System.getProperty("user.dir")+"\\*.pdf");

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

    	}catch(Exception e){

    		e.printStackTrace();

    	}
		return true;
	}
}
