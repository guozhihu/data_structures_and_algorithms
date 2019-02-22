package zhihu.datastructures.set.LinkedList_Set;

import zhihu.datastructures.list.LinkedList;
import zhihu.datastructures.set.Set;

/**
 * Author: zhihu
 * Description: 基于链表实现的Set集合
 * Date: Create in 2019/2/22 13:27
 */
public class LinkedListSet<E> implements Set<E> {
    
    private LinkedList<E> list;
    
    public LinkedListSet(){
        list = new LinkedList<>();
    }
    
    @Override
    public int getSize(){
        return list.getSize();
    }
    
    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    @Override
    public void add(E e){
        if(!list.contains(e))
            list.addFirst(e);
    }
    
    @Override
    public boolean contains(E e){
        return list.contains(e);
    }
    
    @Override
    public void remove(E e){
        list.removeElement(e);
    }
}
