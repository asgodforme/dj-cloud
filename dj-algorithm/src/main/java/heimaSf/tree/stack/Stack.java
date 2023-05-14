package heimaSf.tree.stack;

public interface Stack<E> {

    /**
     * 像栈顶压入元素
     * @param value
     * @return
     */
    boolean push(E value);

    /**
     * 从栈顶弹出元素
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素（不弹出）
     * @return
     */
    E peek();

    boolean isEmpty();

    boolean isFull();
}
