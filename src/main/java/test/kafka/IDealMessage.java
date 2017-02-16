package test.kafka;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kafka
 * company: SiBu
 * create_date: 2017/02/16
 * description :
 */
public interface IDealMessage<T> {
        void dealMQ(T obj);
}
