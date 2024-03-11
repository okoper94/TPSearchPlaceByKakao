package com.kys2024.tpsearchplacebykakao.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kys2024.tpsearchplacebykakao.G
import com.kys2024.tpsearchplacebykakao.R
import com.kys2024.tpsearchplacebykakao.data.UserAccount
import com.kys2024.tpsearchplacebykakao.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityEmailLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btnSignin.setOnClickListener { clickSignin() }

    }
    private fun clickSignin(){
        var email=binding.inputLayoutEmail.editText!!.text.toString()
        var password=binding.inputLayoutPassword.editText!!.text.toString()

        //Firebase Firestore DB에서 이메일 로그인 확인
        val userRef: CollectionReference = Firebase.firestore.collection("emailUsers")
        userRef
            .whereEqualTo("email",email)
            .whereEqualTo("password",password)
            .get().addOnSuccessListener {
                if (it.documents.size>0){//이메일,비밀번호 검색결과가 1개 이상 이므로 찾았다는 것임 로그인 성공

                    //다른화면에서도 회원정보를 사용할 수도 있어서 전역변수처럼 G클래스에 저장
                    val id:String = it.documents[0].id //랜덤하게 만들어진 document 명을 id로 활용
                    G.userAccount= UserAccount(id,email)

                    //로그인 성공했으니 Main화면으로 이동
                    val intent=Intent(this,MainActivity::class.java)

                    //기존 task의 모든 액티비티를 제고하고 새로운 작업시작 task 시작
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)  //화면을 거쳐서 화면에 도달하다보면 그전의 화면을 따로꺼줘야하는데 싹다 지워주는 설정 피니쉬가 안되는 경우
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


                    startActivity(intent)


                }else{
                    //이메일과 비밀번호에 해당하는 document가 없는 것 로그인 실패
                    AlertDialog.Builder(this).setMessage("이메일과 비밀번호를 다시 확인해주세요").create().show()
                    binding.inputLayoutEmail.editText!!.requestFocus()
                    binding.inputLayoutEmail.editText!!.selectAll()
                }
            }

    }


}