		AREA SearchList, CODE, READONLY
		
Value	EQU		0x12347777					; Set search value	
		
		ENTRY
		ADR		r0, List					; Get pointer to linked list
		LDR		r1, =Value					; Get pointer to value we are searching for
		
		LDR		r3, [r0]					; Point to contents of list
		CMP		r3, #0x00					; Check if list is empty (ie. null contents)
		LDREQ	r2, =0xF0F0F0F0				; If empty, set register 2 to "Not Found"
		BEQ		Loop						; And end the program
											
											; Otherwise, traverse list to find search value
Search	LDR		r3, [r0], #4				; Get key of first node in list, and set pointer to address of next node
		CMP 	r3, r1						; Compare key with search value
		BEQ		Done						; If key equals search value, branch to Done 
											; Otherwise, 
		LDRNE	r0, [r0]					; Point to address of next node (or potentially point to terminator)
		CMPNE	r0, #0x00					; Check if r0 is pointing to terminator
		LDREQ	r2, =0xF0F0F0F0				; If so, set register 2 to "Not Found" 
		BEQ		Loop						; Branch to end of program
		BNE		Search						; If end wasn't reached, repeat search process for next node
		
Done	MOV 	r3, r0						; If search value was found, store address of node with matching key
		LDR		r2, =0xFFFFFFFF				; Set register 2 to "Found"
Loop	B		Loop						; End program with infinite loop
;-------------------------------------------------------------------------------------------------
List 	DCD 	0x12341111, Item5			; Entry in linked list
Item2 	DCD 	0x12342222, Item3			; Entry in linked list
Item3 	DCD 	0x12343333, Item4			; Entry in linked list
Item4 	DCD 	0x12344444, Item6			; Entry in linked list
Item5 	DCD 	0x12345555, Item2			; Entry in linked list
Item6 	DCD 	0x12346666, Item7			; Entry in linked list
Item7 	DCD 	0x12347777, 0x00 			; Terminator
		ALIGN
;-------------------------------------------------------------------------------------------------
		END
	