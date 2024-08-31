static int var1;
int var2;
static int func1(){
    extern int var3;
    var1=var1+1;
}
int func2(){
    int* ptr1 = (int*) malloc(sizeof(int));
    int var1;
    var1=var1+1;
    free(ptr1);
}