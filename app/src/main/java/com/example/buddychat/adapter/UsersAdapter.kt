package com.example.buddychat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.buddychat.convertDate
import com.example.buddychat.databinding.UserItemBinding
import com.example.buddychat.ui.users.UsersFragmentDirections
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    private val client = ChatClient.instance()
    private var userList = emptyList<User>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.binding.avatarImageView.setUserData(currentUser)
        holder.binding.usernameTextView.text = currentUser.id
        holder.binding.lastActiveTextView.text = convertDate(currentUser.lastActive!!.time)

        holder.binding.rootLayout.setOnClickListener {
            createNewChannel(currentUser.id,holder) }
    }



    override fun getItemCount(): Int {
        return userList.size
    }

    fun dataSet(list: List<User>){
        userList = list
        notifyDataSetChanged()
    }

    private fun createNewChannel(selectedUser : String,  holder : MyViewHolder) {

       client.createChannel(
               channelType = "messaging",
               members = listOf(client.getCurrentUser()!!.id, selectedUser)
       ).enqueue {result ->
           if (result.isSuccess){
               navigateToChatFragment(holder,result.data().cid)
           }
           else Log.e("userAdapter",result.error().message.toString())

       }
    }

    private fun navigateToChatFragment(holder: MyViewHolder, cid: String) {

        val action = UsersFragmentDirections.actionUsersFragmentToChatFragment(cid)
        holder.itemView.findNavController().navigate(action)
    }

    class MyViewHolder( val binding : UserItemBinding):RecyclerView.ViewHolder(binding.root)
}