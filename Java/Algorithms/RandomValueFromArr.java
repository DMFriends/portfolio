public class RandomValueFromArr
{
    public static void main(String[] args)
    {
        int[] arrInt = {1, 3, 5, 7, 9};
        
        System.out.println(getRandom(arrInt));
        
        String[] arrStr = {"h", "e", "l", "l", "o"};
        
        System.out.println(getRandom(arrStr));
        
        //ArrayList<Integer> arrListInt =
        
        //ArrayList<Integer> arrListInt =
    }
    
    public static int getRandom(int[] array)
    {
        int rnd = (int)(Math.random() * array.length);
        return array[rnd];
    }
    
    public static String getRandom(String[] array)
    {
        int rnd = (int)(Math.random() * array.length);
        return array[rnd];
    }
    
    public static int getRandom(ArrayList<Integer> array)
    {
        int rnd = (int)(Math.random() * array.size());
        return array.get(rnd);
    }
    
    public static String getRandom(ArrayList<String> array)
    {
        int rnd = (int)(Math.random() * array.size());
        return array.get(rnd);
    }
}