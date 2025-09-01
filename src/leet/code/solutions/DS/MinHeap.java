package leet.code.solutions.DS;

import java.util.Arrays;

public class MinHeap {

    int capacity;
    int size;
    int[] array;

    public MinHeap() {
        this.capacity = 10;//default
        this.size = 0;
        this.array = new int[capacity];
    }


    public int peek(){
        if(isEmpty()){
            throw new IllegalStateException("Heap is empty");
        }

        return array[0];//at root
    }

    public int poll(){
        if(isEmpty()){
            throw new IllegalStateException("Heap is empty");
        }

        int polled = array[0];
        array[0] = array[size-1];//swap with the very last element temporary
        size--;//as size diminished as we polled

        heapifyDown();//top down

        return polled;
    }

    public void add(int value){
        resize();
        array[size] = value;
        size++;

        heapifyUp();
    }

    //TOP DOWN
    private void heapifyDown(){//check from root to last, find smaller value and propagate on top

        int indexOfRoot = 0;

        while(getLeftChildIndex(indexOfRoot) < size){//while we traverse till last element

            int smallerIndex = indexOfRoot ;

            if(array[getLeftChildIndex(indexOfRoot)] < array[indexOfRoot]){//if at left is SMALLER value
                smallerIndex = getLeftChildIndex(indexOfRoot);//then curr smaller index will be this left index
            }

            if(array[getRightChildIndex(indexOfRoot)] < array[indexOfRoot]){//if at right index is SMALLER value
                smallerIndex = getRightChildIndex(indexOfRoot);//than this right index is curr SMALLER index
            }

            if(smallerIndex != indexOfRoot){//if not what we currently have

                swap(indexOfRoot, smallerIndex); // swap elements as new SMALLER index value is < less that what we started with at root
                indexOfRoot = smallerIndex;//curr root is now smaller found as we continue in while loop till we exhaust all possibilities

            }else{

                break;//found SMALLEST -> break while

            }
        }

    }

    //BOTTOM UP
    private void heapifyUp(){//check from bottom up looking for BIGGER values and swap till SMALLEST is at root
            int lastIndex = size-1;

            while (getParentIndex(lastIndex) >= 0){//until we reach root node ( which is index = 0)

                int elementAtLastIndex = array[lastIndex];
                int elementAtParentIndex = array[getParentIndex(lastIndex)];

                if(elementAtParentIndex  > elementAtLastIndex){//should be BIGGER at lower levels-> hence swap

                    swap(lastIndex, getParentIndex(lastIndex));

                    lastIndex = getParentIndex(lastIndex);//update last as we have swapped

                }else {//not bigger

                    break;//break while as all LOWER above BIGGER

                }
            }
    }



    private boolean isEmpty(){
        return size == 0;
    }

    private void resize() {
        if(array.length == size){
            array = Arrays.copyOf(array, capacity * 2);
            this.capacity  = capacity * 2;
        }
    }

    private int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;// formula: parent = (childIndex - 1 ) / 2;
    }

    private int getLeftChildIndex(int parentIndex){
        return (2 * parentIndex) + 1;//formula: left child = ( 2 * parentIndex)  +  1
    }

    private int getRightChildIndex(int parentIndex){
        return (2 * parentIndex) + 2;//formula: right child = ( 2 * parentIndex)  +  2
    }

    private void swap(int firstIndex, int secondIndex){
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }


    private  void print(){
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " -> ");
        }
        System.out.println("\r\n\t--------------------------");
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(2);
        minHeap.add(5);

        minHeap.print();

        System.out.println("peek : " + minHeap.peek());

        System.out.println("poll : " + minHeap.poll());

        minHeap.print();

        minHeap.add(1);

        System.out.println("peek : " + minHeap.peek());

        minHeap.print();

    }
}