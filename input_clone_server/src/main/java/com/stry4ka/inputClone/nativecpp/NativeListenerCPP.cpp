#include "com_stry4ka_inputClone_nativecpp_NativeListenerCPP.h"
#include <iostream>
#include <windows.h>
#include <conio.h>
#include <cstdlib>
#include <winuser.h>
#include <winable.h>
#include <jni.h>
#include <stdio.h>
#include <Windowsx.h>

using namespace std;

jobject globalObject;
JNIEnv *globalEnv;
jmethodID eventMoveMouseCallback;
jmethodID eventKeyCallback;
jmethodID eventMouseLButtonCallback;
jmethodID eventMouseRButtonCallback;
jmethodID eventMouseWheelButtonCallback;
bool blockInput = false;
BYTE ANDmaskIcon[] = {};
BYTE XORmaskIcon[] = {};
int systemCursorsList[14] = {
	32512, 32650, 32515, 32649, 32651, 32513, 32648,
	32646, 32643, 32645, 32642, 32644, 32516, 32514};
HCURSOR dumpSystemCursors[14];
POINT lastCursorPositionCurentPC;
POINT lastCursorPositionRemotePC;

LRESULT CALLBACK LowLevelKeyboardProc(int nCode, WPARAM wParam, LPARAM lParam)
{
	PKBDLLHOOKSTRUCT p = (PKBDLLHOOKSTRUCT) lParam;
	if (wParam == WM_KEYDOWN || wParam == WM_SYSKEYDOWN) 
	{       
		globalEnv->CallVoidMethod(globalObject, eventKeyCallback, p->vkCode, true);
		//temporary for check response of callback
		globalEnv->CallVoidMethod(globalObject, eventKeyCallback, 9999, true);
    } 
	if (wParam == WM_SYSKEYUP || wParam == WM_KEYUP) 
	{		
		globalEnv->CallVoidMethod(globalObject, eventKeyCallback, p->vkCode, false);
	}
	return blockInput ? 1 : CallNextHookEx(NULL, nCode, wParam,lParam);
}

LRESULT CALLBACK LowLevelMouseProc(int nCode, WPARAM wParam, LPARAM lParam)
{
	if (blockInput)
	{
		MSLLHOOKSTRUCT * p = (MSLLHOOKSTRUCT *) lParam;
		
		switch(wParam)
		{
			case WM_LBUTTONDOWN: 
			{
				globalEnv->CallVoidMethod(globalObject, eventMouseLButtonCallback, true);
				break;
			}
			case WM_LBUTTONUP:
			{
				globalEnv->CallVoidMethod(globalObject, eventMouseLButtonCallback, false);	
				break;
			}
			case WM_RBUTTONDOWN:
			{
				globalEnv->CallVoidMethod(globalObject, eventMouseRButtonCallback, true);
				break;
			}
			case WM_RBUTTONUP:
			{
				globalEnv->CallVoidMethod(globalObject, eventMouseRButtonCallback, false);
				break;
			}
			case WM_MOUSEMOVE:
			{
				SetCursorPos(p->pt.x, p->pt.y);
				globalEnv->CallVoidMethod(globalObject, eventMoveMouseCallback, p->pt.x, p->pt.y);
				break;
			}
			case WM_MOUSEWHEEL:
			{
				globalEnv->CallVoidMethod(globalObject, eventMouseWheelButtonCallback, GET_WHEEL_DELTA_WPARAM(p->mouseData));
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
 
void setupGlobalEnv(JNIEnv *env, jobject jthis) 
{
	globalEnv = env;
	globalObject = env->NewGlobalRef(jthis);
    jclass objClass = env->GetObjectClass(jthis);
	eventKeyCallback = env->GetMethodID(objClass, "eventKey", "(IZ)V");
	eventMoveMouseCallback = env->GetMethodID(objClass, "eventMoveMouse", "(II)V");
	eventMouseLButtonCallback = env->GetMethodID(objClass, "eventMouseLButton", "(Z)V");
	eventMouseRButtonCallback = env->GetMethodID(objClass, "eventMouseRButton", "(Z)V");
	eventMouseWheelButtonCallback = env->GetMethodID(objClass, "eventMouseWheel", "(I)V");
}
 
void startListeners() 
{
	HINSTANCE hinstExe = GetModuleHandle(NULL);
	HHOOK hhkLowLevelKybd  = SetWindowsHookEx(WH_KEYBOARD_LL, LowLevelKeyboardProc, hinstExe, 0);
	HHOOK hLowlevelProc = SetWindowsHookEx(WH_MOUSE_LL, LowLevelMouseProc, hinstExe, 0);
    MessageLoop();
    UnhookWindowsHookEx(hhkLowLevelKybd);
} 

void removeCursor()
{	
	for(int i = 0; i < 14; i++)
	{
	HCURSOR emptyCursor = CreateCursor(NULL, 0, 0, 1, 1, ANDmaskIcon, XORmaskIcon);
	dumpSystemCursors[i] = CopyCursor(LoadCursor(0, MAKEINTRESOURCEA(systemCursorsList[i])));
	SetSystemCursor(emptyCursor, systemCursorsList[i]);
	}
}
 
void showCursor()
{
	for(int i = 0; i < 14; i++)
	{
		SetSystemCursor(dumpSystemCursors[i], systemCursorsList[i]);
	}
}
 
JNIEXPORT void JNICALL Java_com_stry4ka_inputClone_nativecpp_NativeListenerCPP_setupListener 
(JNIEnv *env, jobject jthis) 
{
	setupGlobalEnv(env, jthis);
	startListeners(); 
	return;
}

JNIEXPORT void JNICALL Java_com_stry4ka_inputClone_nativecpp_NativeListenerCPP_blockInput
  (JNIEnv *, jobject, jboolean block)
{
	blockInput = block;
	if (blockInput)
	{
		removeCursor();
		GetCursorPos(&lastCursorPositionCurentPC);
		SetCursorPos(lastCursorPositionRemotePC.x, lastCursorPositionRemotePC.y);
	} else {
		showCursor();
		GetCursorPos(&lastCursorPositionRemotePC);
		//move cursor to last position
		SetCursorPos(lastCursorPositionCurentPC.x, lastCursorPositionCurentPC.y);
	}
}
//g++ -I"C:\Program Files\Java\jdk-13.0.1\include" -I"C:\Program Files\Java\jdk-13.0.1\include\win32" -shared -o nativelistener.dll NativeListenerCPP.cpp
