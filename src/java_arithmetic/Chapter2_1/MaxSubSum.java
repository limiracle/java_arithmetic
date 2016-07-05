package java_arithmetic.Chapter2_1;

/**
 * 最大子序列和问题
 * 问题描述：给定整数（可以为负数），A1，A2，A3,....,AN,求子序列最大的值
 * 例如：对于整数列：－１，１１，－４，１３，－５，－２　最大的序列值为：２０（１１，－４，１３）
 */
public class MaxSubSum {

    /**
     * 时间复杂度：o(n3)
     * @param iargs
     * @return
     */
    public static int maxSubSum1(int [] iargs){
        int sum=0;
        for(int i=0;i<iargs.length;i++){
            for(int j=i;j<iargs.length;j++){
                int thissum=0;
                for(int k=i;k<j;k++){
                    thissum+=iargs[k];
                    if(thissum>sum){
                        sum=thissum;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 时间复杂度：o(n2)
     * @param iargs
     * @return
     */
    public static int maxSubSum2(int [] iargs){
        int sum=0;
        for(int i=0;i<iargs.length;i++){
            int thissum=0;
            for(int j=i;j<iargs.length;j++){
                thissum+=iargs[j];
                if(thissum>sum){
                    sum=thissum;
                }
            }
        }
        return sum;
    }

    /**
     * 放大招　时间复杂度　O(nlogn)
     * 首先分析数组　-1,11,-4,13,-5,-2
     * 从中间分隔，
     * １.左半边为：－１，１１，－４　　最大值为　１１
     * 2.右半边为：１３，－５，－２　　　　　　　　最大值为１３
     * 3.左半边[-1,11,-4]　从右到左遍历，最大的是-4+11=7
     * 4.右半边[13,-5,-2] 从左向右遍历，最大的是13
     * 比较三个最大 做半边最大，右半边最大，左半边从右向左最大＋右半边从左向右最大
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int maxSubSum3(int[] a,int left,int right){
        int sum=0;
        if(left==right){
            sum=a[left]>0?a[left]:0;
            return sum;
        }
        int center=(left+right)/2;
        int leftMax=maxSubSum3(a,left,center);
        int rightMax=maxSubSum3(a,center+1,right);

        int maxLeftBorder=0,leftBorderSum=0;
        for(int i=center;i>=left;i--){
            leftBorderSum+=a[i];
            if(maxLeftBorder<leftBorderSum){
                maxLeftBorder=leftBorderSum;
            }
        }
        int maxRightBorder=0,rightBordeSumr=0;
        for(int i=center+1;i<=right;i++){
            rightBordeSumr+=a[i];
            if(maxRightBorder<rightBordeSumr){
                maxRightBorder=rightBordeSumr;
            }
        }
        return max3(leftMax,rightMax,maxLeftBorder+maxRightBorder);
    }

    public static int max3(int a,int b,int c){
        int cen=a>b?a:b;
        return c>cen?c:cen;
    }

    /**
     * 这个是终极大招
     * 从开始往后遍历，若[0,i]<0则此元素肯定不为子序列的起点，[0,i]子序列最大值需保存
     * 同理，结束元素<0，则此元素肯定不为子序列的终点
     * @param a
     * @return
     */
    public static int maxSubSum4(int a[]){
        int thisMax=0,maxSum=0;
        for(int i=0;i<a.length;i++){
            thisMax+=a[i];
            if(thisMax>maxSum){
                maxSum=thisMax;
            }
            if(thisMax<0){
                thisMax=0;
            }
        }
        return maxSum;
    }
    public static void main(String args[]){

        System.out.println(maxSubSum2(new int[]{-1,11,-4,13,-5,-2}));

        System.out.println(maxSubSum3(new int[]{-1,11,-4,13,-5,-2},0,5));
        System.out.println(maxSubSum4(new int[]{-1,11,-4,13,-5,-2}));
    }
}
