import java.util.ArrayList;

public class BinaryRec
{
    public static void main(String[] args)
    {
        ArrayList<Integer> x = new ArrayList<Integer>();
        
        x.add(4);
        x.add(5);
        x.add(6);
        x.add(7);
        x.add(8);
        x.add(9);
        
        System.out.println("Item " + x.get(binaryRec(x, 7, 0, x.size() - 1)) + " at index " + binaryRec(x, 7, 0, x.size() - 1));
    }
    
    public static int binaryRec(ArrayList<Integer> x, int target, int start, int end)
    {
        if(start <= end)
        {
            int mid = (start + end) / 2;
    		
    		if (target == x.get(mid))
    		{ 
    			return mid;
    		}
    
            if (target < x.get(mid))
            {
                return binaryRec(x, target, start, mid - 1);
            }
            	
            if (target > x.get(mid))
            {
            	return binaryRec(x, target, mid + 1, end);
            }
        }
        
        return -1;
    }
}