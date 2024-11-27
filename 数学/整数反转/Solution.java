

// 这个方法有点偷懒了，用long来存储，不用考虑溢出的问题了
class Solution {
    public int reverse(int x) {
        long sig = 1;
        if(x<0){
            sig = -1;
            x = -x;
        }
        //此时x是正的
        //不用单独求位数
        long sum = 0;
        while(x>0){
            long digitVal = x%10;
            sum = sum*10+digitVal;
            x = x/10;
        }
        long ret = sig*sum;
        if(ret>2147483647||ret<-2147483648){
            return 0;
        }
        return (int)ret;
    }
}

// 这个看图解就懂了！！！
//https://leetcode.cn/problems/reverse-integer/solutions/211865/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma
class Solution2 {
    public int reverse(int x) {
        int ret = 0;
        while(x!=0){
            int tmp = x%10;
            if(ret>214748364||(ret==214748364&&tmp>7)){
                return 0;
            }
            if(ret<-214748364||(ret==-214748364&&tmp<-8)){
                return 0;
            }
            ret = ret*10+tmp;
            x=x/10;
        }
        return ret;
    }
}