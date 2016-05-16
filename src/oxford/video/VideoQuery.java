package azure.oxford.video;

import azure.oxford.video.Video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MebiuW on 16/5/15.
 */
public class VideoQuery {
    /**
     *
     *
     *
     * 拉取索引文件地址,参数从国parameter以key-value的形式传入
     * @param parameters 参数
     * @return 视频的信息
     */
    public static List<Video> getVideoList(Map<String,String> parameters){
        //TODO add your codes here
        return null;
    }

    /**
     * 和上面不同的是,这个以JSON的形式来做
     * @param jsonarameters
     * @return
     */
    public static List<Video> getVideoList(String jsonarameters){
        //TODO add your codes here
        return null;
    }

    /**
     * 首先第一步的测试
     * @param args
     */
    public static void main(String args[]){
        //假设现在我们拉取所有视频的地址,按照时间排序的视频,降序
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("orderBy","time");
        parameters.put("order","desc");
        parameters.put("filter","none");
        getVideoList(parameters);
    }
}
