#include <string.h>

int main()
{
	char * str = "hello world\n";
	write(1, str, strlen(str));

	return 0;
}
