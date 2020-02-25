#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int main()
{
	char * str = "hello world\n";
	int fd = open("./hello.txt", O_CREAT|O_RDWR);

	write(fd, str, strlen(str));
	close(fd);

	printf("--->> %d\n", fd);
	return 0;
}
