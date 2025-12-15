public class RandomNDigitNum
{
    public static void main(String[] args)
    {
        System.out.println(randomNDigitNum(5));
    }
    
    public static int randomNDigitNum(int n)
    {
        String num = "";
        
        for(int i = 0; i < n; i++)
        {
            int temp = (int)(Math.random() * 9);
            
            num += temp + "";
        }
        
        return Integer.valueOf(num);
    }
}