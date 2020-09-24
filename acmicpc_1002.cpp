#include<cstdio>
// [터렛]
// 두 원 사이의 접점의 개수를 구하면 된다
// d1 < r1-r2, r1-r2 < d2 < r1+r2,  d3 = r1+r2,  r1+r2 < d4
int main(){
    int n;
    int x1, y1, r1, x2, y2, r2;
    scanf("%d", &n);
    for (int i=0; i<n; i++){
        scanf("%d %d %d %d %d %d", &x1, &y1, &r1, &x2, &y2, &r2);
        int d = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
        int rDiff = (r1-r2)*(r1-r2);
        int rSum = (r1+r2)*(r1+r2);
        if(d == 0){
        	if (rDiff == 0){
        		printf("-1\n");
        	}else{
        		printf("0\n");
        	}
        }else{
 
        if ((rDiff < d) && (d < rSum))
            printf("2\n");
        else if ((rDiff == d) || (rSum == d))
            printf("1\n");
        else
            printf("0\n");
        }
    }
}
