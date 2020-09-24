#include<cstdio>
// [어린 왕자]
// 피할 수 없는 경계만 샌다
// 출발지, 도착지에서 나오는 길에서 마주치는 경계는 피할 수 없으며
// 그 경계는 출발지에만 포함하거나 도착지에만 포함하는 경우이다 
void sol(int sx, int sy, int dx,int dy,int n){
	int boundaryCounter = 0;
	for(int j=0; j<n; j++){
		int x, y, r;
		scanf("%d %d %d", &x, &y, &r);
		int sd = r*r-(x-sx)*(x-sx)-(y-sy)*(y-sy); // 출발지와의 관계
		int dd = r*r-(x-dx)*(x-dx)-(y-dy)*(y-dy); // 도착지와의 관계
		if ( (sd<0 && dd>0) || (sd>0 && dd<0) ){ 
			boundaryCounter += 1; // 한쪽에만 속하는 경우 무조건 지나야함
		}
	}
	printf("%d\n", boundaryCounter);
}

int main(){
	int t;
	scanf("%d",  &t);
	for (int i=0; i<t; i++){
		int sx, sy, dx, dy;
		int n;
		scanf("%d %d %d %d", &sx, &sy, &dx, &dy);
		scanf("%d", &n);
		sol(sx, sy, dx, dy, n);
	}
}
