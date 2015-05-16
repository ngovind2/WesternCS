		AREA Palindrome, CODE, READONLY
		ENTRY
	
		LDR	r0, =TestStr	;Set r0 to point to TestStr
		LDR r5, =EoS		;Set r5 to point to EoS
		MOV r1,#-1			;Set pointer in r1 to -1 (will be incremented to 0)
toLower	EQU	32				;Add 32 to convert uppercase to lowercase letters in ASCII
		
							;REPEAT
LEN		LDRB r3,[r0,r2]		;   Load a byte of TestStr (character at position pointed at by r2, initially 0)
		CMP	r3,	r5			;   Check if this character is the null character (end of string)
		BEQ	Check			;   If so, stop length count and jump out of loop
		ADD	r2,#1			;   Else, increment pointer to point at next character
		B LEN				;UNTIL end of string (EoS) is reached & r2 points at EoS
	
							;Pointers (r1 and r2) are each at opposite ends of the string
Check	CMP	r1,r2			;Check if pointers have crossed past each other yet
		BGT	IsPal			;If so, corresponding letter pairs have all matched & string is a palindrome
		ADD	r1,#1			;Increment pointer nearest beginning of string
		SUB	r2,#1			;Decrement pointer nearest end of string
		
							;REPEAT
GetCh1	LDRB r3,[r0,r1]		;  	Get character 1 at position pointed at by r1
		CMP	r3,#'A'			;   Check if character 1 is possibly not a letter (less than 'A')
		ADDLT r1,#1			;   If it is possibly not a letter, increment this pointer
		BLT	GetCh1			;   Get next character
		
		CMP r3,#'z'			;   Check if character is greater than 'z'
		ADDGT r1,#1			; 	If so, character is not a letter so increment pointer and get next character
		BGT GetCh1			;UNTIL character is a letter

		CMP	r3,#'a'			;Check if character 1 is uppercase (less than 'a')
		ADDLT r3,#toLower	;If so, convert character to lowercase equivalent (by adding 32)

							;REPEAT
GetCh2	LDRB r4,[r0,r2]		;   Get character 2 at position pointed at by r2
		CMP r4,#'A'			;   Check if character 2 is possibly not a letter (less than 'A')
		SUBLT r2,#1			;   If it is possibly not a letter, decrement this pointer
		BLT GetCh2			;	Get next character
		
		CMP r4,#'z'			;   Check if character 2 is greater than 'z'
		SUBGT r2,#1			; 	If so, character is not a letter so decrement pointer and get next character
		BGT GetCh2			;UNTIL character is a letter

		CMP r4,#'a'			;Check if character 2 is uppercase (less than 'a)
		ADDLT r4,#toLower	;If so, convert character to lowercase equivalent (by adding 32)
							
							;Now have two lowercase letters in r3 and r4
		CMP r3,r4			;Compare character 1 and character 2
		BEQ Check			;If they are equal, string is possibly a palindrome. Continue comparing character pairs.

							;If they are not equal, the string cannot be a palindrome
     	MOV r0,#0 			;Set r0 to 0 to indicate TestStr is not a palindrome
		B EndLoop 			;Skip to end (infinite loop)

IsPal	MOV	r0,#1 			;Set r0 to 1 to indicate TestStr is a palindrome
EndLoop	B EndLoop 			;End program with infinite loop to avoid error

TestStr	DCB	"#No$$$o!n"		;Test if this string is a palindrome
EoS		DCB 0x00			;End of string ASCII value

	END