/*
341. 扁平化嵌套列表迭代器
给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。



示例 1:

输入: [[1,1],2,[1,1]]
输出: [1,1,2,1,1]
解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
示例 2:

输入: [1,[4,[6]]]
输出: [1,4,6]
解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 */
package 数据结构;
import java.util.*;
public class NestedIterator implements Iterator<Integer> {
 /*
 Solution:  通过构建一个栈，递归的解析每一个element: 若element是Integer，直接入栈；若为NestedList，继续递归解析
  */
    private class NestedInteger{
        public boolean isInteger(){
            return false;
        }
        public Integer getInteger(){
            return null;
        }
        public List getList(){
            return null;
        }
    }

    public List<NestedInteger> nestedList;
    private Stack<Integer> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.stack = new Stack();
        evalNestedList(nestedList);
    }

    private void evalNestedList(List<NestedInteger> nestedList){
        for(int i = nestedList.size() - 1; i >= 0 ; i --){
            if (nestedList.get(i).isInteger())
                stack.push(nestedList.get(i).getInteger());
            else{
                evalNestedList(nestedList.get(i).getList());
            }
        }
    }

    @Override
    public Integer next() {
        return stack.pop();
    }

    @Override
    public boolean hasNext() {
        return stack.size() != 0;
    }
}
