package nlp;
import org.fnlp.app.keyword.AbstractExtractor;
import org.fnlp.app.keyword.WordExtract;
import org.fnlp.nlp.cn.CNFactory;
import org.fnlp.nlp.cn.tag.CWSTagger;
import org.fnlp.nlp.corpus.StopWords;

import java.util.Map;

/**
 * Created by MebiuW on 16/5/19.
 */
public class Test {
    public static void main(String args[]) throws  Exception{
        // 创建中文处理工厂对象，并使用“models”目录下的模型文件初始化
        String path="/Users/MebiuW/Documents/Doing/PKU/JushuoServer/lib/fundannlp/models";
        CNFactory factory = CNFactory.getInstance(path);

        // 使用分词器对中文句子进行分词，得到分词结果
        String input="NVIDIA最近发布了Pascal架构的Tesla P100加速卡及GTX 1080显卡，强大的性能让人折服，不过同样强大的价格也让人感慨NVIDIA这日子也过得太舒服了，这是欺负AMD没有高端卡发布啊！不过AMD虽然低调，但绝对是闷声发大财，其显卡市场份额已经止损并开始回升，移动显卡市场份额涨了7.3个百分点，桌面独显份额也回升1.8个百分点。";
        String[] words = factory.seg(input);

        // 打印分词结果
        for(String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();

        StopWords sw= new StopWords(path+"/stopwords");
        CWSTagger seg = new CWSTagger(path+"/seg.m");
        AbstractExtractor key = new WordExtract(seg,sw);

        //you need to set the keywords number, here you will get 10 keywords
        Map<String,Integer> ans = key.extract(input, 10);
        System.out.println(ans.toString());
    }
}
