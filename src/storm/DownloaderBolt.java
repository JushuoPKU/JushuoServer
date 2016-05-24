package storm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 根据上一步得到的url和tid,将视频文件下载到一个对应的位置
 * Created by MebiuW on 16/5/24.
 */
public class DownloaderBolt extends BaseRichBolt {
    OutputCollector outputCollect;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollect = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        //使用Wget将他按照tid进行下载
        String url = tuple.getStringByField("url");
        String tid = tuple.getStringByField("tid");
        String suffix = url.substring(url.lastIndexOf("."));
        //将文件下载,并按照tid保存到零时目录下
        RuntimeWorker.execute("cd ~/server/raws");
        boolean flag = RuntimeWorker.execute("wget -O "+tid+suffix+"  "+url+" ");
        //将下载好的文件地址,输出给下一个地址,方便其进行分割
        if(flag){
            List<Object> nextTuple = new ArrayList<>();
            nextTuple.add("~/server/raws/"+tid+suffix);
            outputCollect.emit(nextTuple);
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("filePosition"));
    }
}
