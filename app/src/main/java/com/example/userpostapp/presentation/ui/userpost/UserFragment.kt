package com.example.userpostapp.presentation.ui.userpost

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.userpostapp.R
import com.example.userpostapp.databinding.FragmentUserBinding
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.presentation.ui.userpost.adapter.UserAdapter
import com.example.userpostapp.util.common.Errors
import com.example.userpostapp.util.common.error.ErrorMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var errorMessage: ErrorMessage

    private val adapter: UserAdapter = UserAdapter(onItemClick = this::goToDetail)

    private val viewModel: UserViewModel by viewModels()
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initViews() {
        initViewStateObserver()
        setupRecycler()
        loadUsers()
    }

    private fun setViewState(viewState: UserViewState) {
        when (viewState) {
            is ShowError -> showError(viewState.error)
            ShowLoading -> showLoader()
            is ShowUsers -> showSuccessSearchUsers(viewState.data)
        }
    }

    private fun showSuccessSearchUsers(data: List<UserModel>) {
        hideLoader()
        hideError()
        setDataInList(data)
    }

    private fun setDataInList(data: List<UserModel>) {
        adapter.submitList(data)
        if (data.isNullOrEmpty()){
            Toast.makeText(activity, getString(R.string.list_empty), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecycler() {
        binding.rvUserList.adapter = adapter
    }

    private fun initViewStateObserver() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::setViewState))
    }

    private fun loadUsers() {
        hideError()
        viewModel.getUsers()
    }

    private fun loadUsersByQuery(query: String) {
        hideError()
        viewModel.getUsersByQuery(query)
    }

    private fun showLoader() {
        binding.pbProgress.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.pbProgress.visibility = View.GONE
    }

    private fun showError(error: Errors) {
        hideLoader()
        val message = errorMessage.getMessage(error)
        binding.vError.root.visibility = View.VISIBLE
        binding.vError.tvErrorMessage.text = message
        binding.vError.btnErrorRetry.setOnClickListener {
            loadUsers()
        }
    }

    private fun hideError() {
        binding.vError.root.visibility = View.GONE
    }


    private fun goToDetail(user: UserModel) {
        val action = UserFragmentDirections.actionUserFragmentToUserDetailFragment(user.id)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        search(newText)
        return true
    }

    private fun search(s: String?) {
        if (s.isNullOrEmpty()){
            loadUsers()
        }else {
            loadUsersByQuery(s)
        }
    }
}