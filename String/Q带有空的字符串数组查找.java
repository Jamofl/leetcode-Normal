package String;
/*
【Java】有个排序后的字符串数组，其中散布着一些空字符串，编写一个方法，找出给定字符串的位置

michellechouu 2015-07-22 14:18:42  1179  收藏
分类专栏： java
版权
有个排序后的字符串数组，其中散布着一些空字符串，编写一个方法，找出给定字符串的位置

如果没有空字符串，可以直接用二分查找法，但是有空字符串是不是就不行了呢？

我们可以对二分查找法做一些改进，如果mid对应的是空字符串，就把mid移到离它最近的非空字符串的index。
————————————————
版权声明：本文为CSDN博主「michellechouu」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/michellechouu/article/details/47002789
 */
public class Q带有空的字符串数组查找 {
    public int searchRE(String[] strings, String str, int first, int last) {
        if (first > last) return -1;
        int mid = (first + last) / 2;
        if( strings[mid].isEmpty() ) {
            int left = mid - 1;
            int right = mid + 1;
            while(true) {
                if (left < first && right > last) return -1;
                else if ( right <= last && !strings[right].isEmpty() ) {
                    mid = right;
                    break;
                }
                else if ( left >= first && !strings[left].isEmpty() ) {
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }

        if( str.equals(strings[mid]) ) { return mid; }
        else if ( strings[mid].compareTo(str) < 0) {
            return searchRE( strings, str, mid + 1, last);
        }
        else {
            return searchRE( strings, str, first, mid - 1);
        }
    }


    public int search(String[] strings, String str) {
        if (strings == null || str == null) {
            return -1;
        }

        return searchRE(strings, str, 0, strings.length - 1);
    }

}
