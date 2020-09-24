#include<cstdio>
#include<queue>
#include<map>
using namespace std;
// [ACM Craft]
// 해당 건물을 짓기위한 시간은
// 필요 건물을 짓는 가장 오래걸린 시간 + 해당건물 시간이다
// 따라서 totalcost = max(이전 건물 cost) + 이번 cost
multimap<int, int> orders; // (건물, 필요건물)
int cost[1001]; // 비용
int totalcost[1001]; // 해당 레벨까지의 비용
void build(int k){
	if (totalcost[k] < 0){
		if(orders.count(k) == 0){
			totalcost[k] = cost[k];
			return;
		}
		int maxcost = 0;
		auto pre = orders.find(k);
		for(; pre != orders.end(); pre++){
			if(pre->first != k) break;
			build(pre->second);
			if(maxcost < totalcost[pre->second]) // 이전 레벨까지 짓기 위한
				maxcost = totalcost[pre->second];// 가장 비싼 값 선택
		}
		totalcost[k] = maxcost + cost[k]; // 총 비용
	}
}

void solution(int n, int k){
	int w;				 // object
	orders.clear();
	for(int i=0; i<1001;i++)totalcost[i]=-1; //init
	
	multimap<int,int> o; // orders
	for (int i=0; i<n; i++){
		scanf("%d", cost+i+1);
	}
	for (int i=0; i<k; i++){
		int a, b;
		scanf("%d %d", &b, &a);
		orders.insert(make_pair(a,b));
	}
	scanf("%d", &w);
	build(w);
	printf("%d\n", totalcost[w]);
}
int main(){
	int t;
	scanf("%d", &t);
	for (int i=0; i<t; i++){
		int n, k;
		scanf("%d %d", &n, &k);
		solution(n, k);	
	}
}
