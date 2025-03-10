import java.util.BitSet;

/**
 * @description: MyBloomFilter
 * @author: 庄霸.liziye
 * @create: 2022-04-01 16:50
 **/
public class MyBloomFilter {

    /**
     * 一个长度为256的比特位
     */
    private static final int DEFAULT_SIZE = 1 << 30; // 左移一位代表乘以2  左移8位等价于乘以2 ^ 8 = 256

    /**
     * 为了降低错误率，使用加法hash算法，所以定义一个8个元素的质数数组
     */
    private static final int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};

    /**
     * 相当于构建8个不同的hash算法
     */
    private static HashFunction[] functions = new HashFunction[seeds.length];

    /**
     * 初始化布隆过滤器的bitmap,即位数组
     */
    private static BitSet bitset = new BitSet(DEFAULT_SIZE);

    /**
     * 添加数据
     *
     * @param value 需要加入的值
     */
    public static void add(String value) {
        if (value != null) {
            for (HashFunction f : functions) {
                //计算 hash 值并修改 bitmap 中相应位置为 true
                bitset.set(f.hash(value), true);
            }
        }
    }

    /**
     * 判断相应元素是否存在
     * @param value 需要判断的元素
     * @return 结果
     */
    public static boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (HashFunction f : functions) {
            ret = bitset.get(f.hash(value));
            //一个 hash 函数返回 false 则跳出循环
            if (!ret) {
                break;
            }
        }
        return ret;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {

        for (int i = 0; i < seeds.length; i++) {
            functions[i] = new HashFunction(DEFAULT_SIZE, seeds[i]);
        }

        //添加00个元素
        for (int i = 0; i < 100; i++) {
            add(String.valueOf(i));
        }
        String id = "12345";
        add(id);
        System.out.println("元素 12345 是否存在：" + contains(id));
        System.out.println("元素 234567890 是否存在：" + contains("234567890"));
    }
}

class HashFunction {

    private int size;
    private int seed;

    public HashFunction(int size, int seed) {
        this.size = size;
        this.seed = seed;
    }

    public int hash(String value) {
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++) {
            result = (seed * result + value.charAt(i)) % size;
        }
        return result;
    }
}

