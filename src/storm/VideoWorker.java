package storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import org.json.JSONObject;

import java.util.Map;

/**
 * 根据视频地址做一个拆分
 * Created by MebiuW on 16/5/24.
 */
public class VideoWorker extends BaseRichBolt {
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple tuple) {
        String file = tuple.getStringByField("filePosition");
        //调用查询视频的长度信息
        String videoInfor = RuntimeWorker.query("ffprobe -v quiet -print_format json -show_format -show_streams " + file);
        JSONObject jsonInfor = new JSONObject(videoInfor);
        String suffix = file.substring(file.lastIndexOf("."));
        double duration = jsonInfor.getJSONArray("streams").getJSONObject(0).getDouble("duration");
        //按照15S对他进行拆分
        for(int i=0;i<duration;i+=15){
            RuntimeWorker.query("ffmpeg -ss "+i*15+" -t"+15+" -i "+file+" -acodec copy -vcodec copy tid#"+i+suffix);
        }
        //截取对应的图片和进行音频的转化
        for(int i=0;i<duration;i+=15){
            RuntimeWorker.query("ffmpeg -ss "+i*15+" -t"+15+" -i "+file+" -acodec copy -vcodec copy tid#"+i+suffix);
        }




    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}