import org.json.JSONObject;
import storm.RuntimeWorker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MebiuW on 16/5/24.
 */
public class Tester {
    public static void downloaderTest(){
        //使用Wget将他按照tid进行下载
        String url = "http://mobile.zol.com.cn/584/5845541.html";
        String tid= "1111";
        String suffix = url.substring(url.lastIndexOf("."));
        //将文件下载,并按照tid保存到零时目录下
        RuntimeWorker.execute("cd ~/server/raws");
        boolean flag = RuntimeWorker.execute("wget -O "+tid+suffix+"  "+url+" ");

    }

    public static void videoworkerTest(){
        String file = "~/video/a6300.mp4";
        String tid = "23423423";
        
        //将文件下载,并按照tid保存到零时目录下
        RuntimeWorker.execute("cd ~/server/raws");
        //调用查询视频的长度信息
        String videoInfor = RuntimeWorker.query("ffprobe -v quiet -print_format json -show_format -show_streams " + file);
        JSONObject jsonInfor = new JSONObject(videoInfor);
        String suffix = file.substring(file.lastIndexOf("."));
        double duration = jsonInfor.getJSONArray("streams").getJSONObject(0).getDouble("duration");
        //按照15S对他进行拆分
        for(int i=0;i<duration;i+=15){
            RuntimeWorker.query("ffmpeg -ss "+i*15+" -t"+15+" -i "+file+" -acodec copy -vcodec copy "+tid+"#"+i+suffix);
        }
        //截取对应的图片和进行音频的转化 视频每段->mp3->amr
        for(int i=0;i<duration;i+=15){
            String subfilename=tid+"#"+i+suffix;
            String tmpname = tid+"#"+i+".mp3";
            String finalname = tid+"#"+i+".amr";
            RuntimeWorker.query("ffmpeg -i "+subfilename+" -acodec copy -vn "+tmpname);
            RuntimeWorker.query("ffmpeg -i "+tmpname+" -ar 8000 -ab 12.2k -ac 1 "+finalname);
        }

    }
    public static void main(String args[]){
        System.out.println("Hello World-Say");
        System.out.println("########Downloader Test##############");
        downloaderTest();
        System.out.println("########Video Test##############");
        videoworkerTest();

    }
}
