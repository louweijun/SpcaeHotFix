#include <jni.h>
#include <string>

JNIEXPORT void JNICALL
Java_com_space_self_hotfix_HotfixManager_hotFix(JNIEnv *env, jobject instance, jobject src,
                                                jobject dest) {

    // TODO

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_space_self_hotfix_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
