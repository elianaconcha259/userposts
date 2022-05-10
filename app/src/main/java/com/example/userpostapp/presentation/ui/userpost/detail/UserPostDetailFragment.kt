package com.example.userpostapp.presentation.ui.userpost.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.userpostapp.databinding.FragmentUserpostdetailBinding
import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.presentation.ui.product.detail.*
import com.example.userpostapp.presentation.ui.userpost.adapter.detail.UserPostAdapter
import com.example.userpostapp.util.common.Errors
import com.example.userpostapp.util.common.error.ErrorMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserPostDetailFragment : Fragment() {
    @Inject
    lateinit var errorMessage: ErrorMessage
    private val args: UserPostDetailFragmentArgs by navArgs()
    private val viewModel: UserDetailViewModel by viewModels()

    private val adapter: UserPostAdapter = UserPostAdapter()

    private val viewModelPost: UserPostViewModel by viewModels()
    private lateinit var binding: FragmentUserpostdetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserpostdetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        initViewStateObserver()
        loadUserDetail()
        setupRecycler()
        loadPosts()
    }

    private fun loadPosts() {
        hideError()
        viewModelPost.getPostByIdUser(args.id)
    }

    private fun loadUserDetail() {
        viewModel.getProduct(args.id)

    }

    private fun setViewState(viewState: UserDetailViewState) {
        when (viewState) {
            ShowLoading -> showLoader()
            is ShowError -> showError(viewState.error)
            is ShowUser -> showDetail(viewState.data)
            is ShowPostUser -> showSuccessSearchPosts(viewState.data)
        }
    }

    private fun showSuccessSearchPosts(data: List<PostModel>) {
        hideLoader()
        hideError()
        setDataInList(data)
    }

    private fun setDataInList(data: List<PostModel>) {
        adapter.submitList(data)
    }

    private fun showDetail(user: UserModel){
        binding.tvNameUser.setText(user.name)
        binding.tvEmailUser.setText(user.email)
        binding.tvPhoneUser.setText(user.phone)
    }

    private fun showLoader() {
        binding.pbProgress.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.pbProgress.visibility = View.GONE
    }

    private fun setupRecycler() {
        binding.rvUserPostList.adapter = adapter
    }

    private fun initViewStateObserver() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::setViewState))
        viewModelPost.viewState.observe(viewLifecycleOwner, Observer(::setViewState))
    }

    private fun showError(error: Errors) {
        val message = errorMessage.getMessage(error)
        binding.vError.root.visibility = View.VISIBLE
        binding.vError.tvErrorMessage.text = message
        binding.vError.btnErrorRetry.setOnClickListener {
            loadUserDetail()
        }
    }

    private fun hideError() {
        binding.vError.root.visibility = View.GONE
    }

}