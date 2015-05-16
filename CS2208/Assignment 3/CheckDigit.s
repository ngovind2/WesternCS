		AREA CheckDigit, CODE, READONLY
		ENTRY
		
		ADR	r0, UPC					;Set r0 to point to UPC	
toInt	EQU	48						;Subtract 48 from ASCII value to get corresponding integer value (eg. '0' - 48 = 0)
	
									;REPEAT
AddOdd	LDRB r2, [r0, r1]			;   Load the byte of UPC at position pointed to by r1 (initially at 0)	
		SUB r2, #toInt				;   Convert byte's ASCII value into integer	('0' starts at ASCII 48)
		ADD r3, r2					;   Add integer value to running total (initially at 0)
		ADD	r1, #2					;   Increment counter by 2 to point to next odd-positioned digit
		CMP	r1, #12					;	Check position of r1 pointer; if past UPC, exit or otherwise, loop
		BNE AddOdd					;UNTIL loop is complete and odd-positioned digits are summed	
		MOV	r1, #1					;Reset pointer to 1 (2nd digit of UPC) to sum even-positioned digits
		
									;REPEAT
AddEven	LDRB r2, [r0, r1]			;   Load the byte of UPC at position pointed to by r1
		SUB	r2, #toInt				;   Convert byte's ASCII value into integer	('0' starts at ASCII 48)	
		ADD r4, r2					;   Add integer value to running total (initially at 0)
		ADD	r1, #2					;   Increment counter by 2 to point to next odd-positioned digit
		CMP	r1, #11					;	Check position of r1 pointer; if pointing at check digit, exit or otherwise, loop
		BNE AddEven					;UNTIL loop is complete and even-positioned digits except check digit are summed
		
		ADD	r3, r3, r3, LSL #1		;Multiply sum of odd-positioned digits by 2 via left shift, then add it to r3
									;Analogous to multiplying original sum by 3
		ADD	r4, r3					;Add even and odd sums calculated above, and store in r4
		SUB r4,#1			 		;Subtract 1 from total of odd & even sums stored in r4
		
									;REPEAT
Divide	CMP r4,#9					;   Check if total is equal to or less than 9
		SUBGT r4, #10				;   If greater than 9, subtract 10 from total
		BHI Divide					;UNTIL remaining total is 9 or less
									
		RSB r5,r4,#9				;Subtract the remainder from 9, store in r5 (calculated check digit)
		LDRB r6,[r0,r1]				;Load UPC check digit into r6 (r1 is already pointing at check digit position)
		SUB r6, #toInt				;Subtract 48 from check digit's ASCII value to obtain its integer value 
		MOV r0,#0					;Set r0 to 0 - if check digits match, this will change accordingly (if not, stays at 0)
		CMP r5,r6					;Compare UPC check digit with calculated check digit
		MOVEQ r0,#1					;Store 1 in r0 if check digits match and UPC is valid

Loop	B	Loop					;Infinite loop to avoid error
		
UPC 	DCB "013800150738"		 	;UPC string
		END