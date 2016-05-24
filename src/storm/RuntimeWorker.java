package storm;

/**
 * Created by MebiuW on 16/5/24.
 */
public class RuntimeWorker {
    public static boolean execute(String commands){
        Runtime runtime = Runtime.getRuntime();
        try{
            Process process=runtime.exec(commands);
            //检查是否执行失败
            if(process.waitFor()!=0){
                if(process.exitValue() == 1){
                    return false;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();

            return false;
        }
        return true;
    }
}
