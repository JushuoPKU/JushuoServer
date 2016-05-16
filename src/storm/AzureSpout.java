package storm;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 这个Topology主要是需要从数据库读取内容
 * Created by MebiuW on 16/5/14.
 */
public class AzureSpout extends BaseRichSpout {
    SpoutOutputCollector spoutOutputCollector;
    TopologyContext topologyContext;
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        this.topologyContext = topologyContext;
    }

    @Override
    public void nextTuple() {
        /**
         * 从Azure上获得刚处理完的视频进过
         */
        List<Object> tuple = new ArrayList<>();
        //增加tid和视频地址
        tuple.add("tid");
        tuple.add("url");
        spoutOutputCollector.emit(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("tid","baseVideoUrl"));
    }
}
