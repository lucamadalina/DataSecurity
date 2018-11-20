#include<stdio.h>
#include<string.h>

int main(){
    char msg[20];
    char key[] = "HELLO";
    int msgLen = strlen(msg);
    int keyLen = strlen(key);
    int i;
    int j;

    char newKey[msgLen];
    char encryptedMsg[msgLen];

    printf("Text Plain: ");
    scanf("%s", &msg);

    for(i = 0, j = 0; i < msgLen; ++i, ++j){
        if(j == keyLen)
            j = 0;

        newKey[i] = key[j];
    }

    newKey[i] = '\0';

    for(i = 0; i < msgLen; ++i)
        encryptedMsg[i] = ((msg[i] + newKey[i]) % 26) + 'A';

    encryptedMsg[i] = '\0';

    printf("Original Message: %s", msg);
    printf("\nKey: %s", key);
    printf("\nNew Generated Key: %s", newKey);
    printf("\nEncrypted Message: %s", encryptedMsg);

    return 0;
}
