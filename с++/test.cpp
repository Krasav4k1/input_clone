#include <iostream>
#include <windows.h>
#include <conio.h>
#include <cstdlib>
#include <winuser.h>
#include <winable.h>
#include <map>
#include <stdio.h>
#include <Windowsx.h>

using namespace std;

static bool blockInput = false;
BYTE ANDmaskIcon[] = {};
BYTE XORmaskIcon[] = {};
int systemCursorsList[14] = {32512, 32650, 32515, 32649, 32651, 32513, 32648, 32646,
32643, 32645, 32642, 32644, 32516, 32514};
HCURSOR dumpSystemCursors[14];

LRESULT CALLBACK LowLevelKeyboardProc(int nCode, WPARAM wParam, LPARAM lParam)
{
	PKBDLLHOOKSTRUCT p = (PKBDLLHOOKSTRUCT) lParam;
	    std::cout << p->vkCode;
		if (p->vkCode == 27 && wParam == WM_KEYDOWN) {
			blockInput = !blockInput;
		}
	return blockInput ? 1 : CallNextHookEx(NULL, nCode, wParam,lParam);
}

 
LRESULT CALLBACK LowLevelMouseProc(int nCode, WPARAM wParam, LPARAM lParam)
{
//    MSLLHOOKSTRUCT * pMouseStruct = (MSLLHOOKSTRUCT *) lParam;
     MSLLHOOKSTRUCT  * pMouseStruct = ( MSLLHOOKSTRUCT  *) lParam;
 //   printf("Mouse position X = %d  Mouse Position Y = %d\n", pMouseStruct->pt.x, pMouseStruct->pt.y);
	if (blockInput) 
	{
		switch(wParam)
		{
			case WM_LBUTTONDOWN: 
			{
				std::cout<< " WM_LBUTTONDOWN ";
				break;
			}
			case WM_LBUTTONUP:
			{
				std::cout<< " WM_LBUTTONUP ";
				break;
			}
			case WM_MOUSEMOVE:
			{
				SetCursorPos(pMouseStruct->pt.x, pMouseStruct->pt.y);
				break;
			}
			case WM_MOUSEWHEEL:
			{
		
				std::cout<< " WM_MOUSEWHEEL " << GET_WHEEL_DELTA_WPARAM(pMouseStruct->mouseData) <<" ";
				break;
			}
			case WM_RBUTTONDOWN:
			{
				std::cout<< " WM_RBUTTONDOWN ";
				break;
			}
			case WM_RBUTTONUP:
			{
				std::cout<< " WM_RBUTTONUP ";
				break;
			}
		}
	}
    return blockInput ? 1 : CallNextHookEx(NULL, nCode, wParam,lParam);
}
 
void MessageLoop()
{
    MSG message;
    while (GetMessage(&message,NULL,0,0)) 
	{
        TranslateMessage( &message );
        DispatchMessage( &message );
    }
}
 
void startListener() 
 {
	HINSTANCE hinstExe = GetModuleHandle(NULL);
	HHOOK hhkLowLevelKybd  = SetWindowsHookEx(WH_KEYBOARD_LL, LowLevelKeyboardProc, hinstExe, 0);
	HHOOK hLowlevelProc = SetWindowsHookEx(WH_MOUSE_LL, LowLevelMouseProc, hinstExe, 0);
    MessageLoop();
    UnhookWindowsHookEx(hhkLowLevelKybd);
 }
 

 
void changecursor()
{
	for(int i = 0; i < 14; i++)
	{
		HCURSOR emptyCursor = CreateCursor(NULL, 1, 1, 1, 1, ANDmaskIcon, XORmaskIcon);
		dumpSystemCursors[i] = CopyCursor(LoadCursor(0, MAKEINTRESOURCEA(systemCursorsList[i])));
		SetSystemCursor(emptyCursor, systemCursorsList[i]);
	}
	for(int i = 0; i < 14; i++)
	{
		SetSystemCursor(dumpSystemCursors[i], systemCursorsList[i]);
	}
}

int main(int argc, char **argv) 
{	
//	changecursor();
	startListener();	
    return 1;
}

