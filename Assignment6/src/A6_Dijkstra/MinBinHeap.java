package A6_Dijkstra;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	                                              //be null. This is ok! Just build out 
	                                              //from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
	    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
	                                             //of child/parent computations...
	                                             //the book/animation page both do this.
	  }
	    
	  //Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	  }

	@Override
	public void insert(EntryPair entry) {
		// TODO Auto-generated method stub
		if(size==0){
			array[1]=entry;
			size++;
		}
		else{
			for(int i=size+1; i>0;i/=2){
				//int temp=array[i/2].getPriority();
				//int tempE= entry.getPriority();
				if(array[i/2].getPriority()>entry.getPriority()){
					array[i]=array[i/2];
				}
				else{
					array[i]=entry;
					break;
				}
			
			}
			size++;
		}
//		int hole= ++size;
//		for(array[0]=entry; entry.compareTo(array[hole/2])<0;hole/=2)
//			array[hole]=array[hole/2];
//		array[hole]=entry;
		
	}
	public void delMin(){
		if(size!=0){
			array[1]=array[size--];
			bubbleDown(1);
		}
	}

	
	public void bubbleDown(int hole){
		int nino;
		EntryPair temp=array[hole];
		
		for(; hole*2<= size; hole=nino){
			nino=hole*2;
			if (nino !=size&& array[nino+1].getPriority()<array[nino].getPriority())
				nino++;
			if(array[nino].getPriority()<temp.getPriority()){
				array[hole]=array[nino];
			}
			else break;
		}
		array[hole]=temp;
		
	}


	@Override
	public EntryPair getMin() {
		// TODO Auto-generated method stub
		return array[1];
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		// TODO Auto-generated method stub
		//array=entries;
		int i=1;
		for (EntryPair x:entries){
			array[i]=x;
			i++;
		}
		
		size=entries.length;
		for(int j=size/2;j>0;j--){
			//array[i]=entries[i];
			bubbleDown(j);
		}
			
	}
}

//	@Override
//	public EntryPair[] getHeap() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//}

//BUBBLEDOWN		
//for(int i=hole; i*2<=size;i=nino){
//	nino=i*2;
//	if(nino!=size && 
//			array[nino+1].getPriority()<array[nino].getPriority()){
//		nino++;
//	}
//	if (array[nino].getPriority()<temp.getPriority())
//		array[hole]=array[nino];
//	else 
//		break;
//}
//array[hole]=temp;
//}
//                    DELMIN
//@Override
//public void delMin() {
//	EntryPair temp;
//	if(size==1){
//		array[1]=null;
//		size--;
//	}
//	else{
//		//			array[1]=array[size];
//		//			array[size]=null;
//		//			for(int i=1;i<size/2;i*=2){
//		//				if(array[i*2+1]!=null){
//		//					temp=array[i];
//		//				}
//		//					
//		//			}
//		array[1]=array[size];
//		array[size]=null;
//		for(int i=1; i<size/2; i=i*2){
//			if(array[i*2].getPriority()<array[i*2+1].getPriority() || 
//					array[i*2+1]==null){
//				if(array[i].getPriority()>array[i*2].getPriority()){
//					temp=array[i];
//					array[i]=array[i*2];
//					array[i*2]=temp;
//				}
//			}else{
//				if(array[i].getPriority()>array[i*2+1].getPriority()){
//					temp=array[i];
//					array[i]=array[i*2+1];
//					array[i*2+1]=temp;
//					i++;
//				}
//			}
//		}
//		size--;
//	}
//}
	// TODO Auto-generated method stub
//	//EntryPair minEn =getMin();
//	if(array[1]==null)
//		//no hace nada;
//	array[1]= array[size];
//	bubbleDown(1);
//	
//	
//}
////
