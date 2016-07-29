package com.akexorcist.mvpsimple.module.writer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.BlogInfo;

import org.parceler.Parcels;

public class BlogInfoActivity extends AppCompatActivity implements BlogInfoContractor.View {
    public static final String KEY_BLOG_INFO = "key_blog_info";
    private BlogInfoContractor.Presenter presenterWriterInfoContractor;
    private TextView tvBlogId;
    private TextView tvBlogName;
    private TextView tvBlogDescription;
    private TextView tvBlogUrl;
    private TextView tvBlogPostCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_info);

        bindView();
        setupView();
        createPresenter();

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
            restoreView();
        } else {
            restoreArgument(getIntent().getExtras());
            initialize();
        }
    }

    private void bindView() {
        tvBlogId = (TextView) findViewById(R.id.tv_blog_id);
        tvBlogName = (TextView) findViewById(R.id.tv_blog_name);
        tvBlogDescription = (TextView) findViewById(R.id.tv_blog_description);
        tvBlogUrl = (TextView) findViewById(R.id.tv_blog_url);
        tvBlogPostCount = (TextView) findViewById(R.id.tv_blog_post_count);
    }

    private void setupView() {
    }

    private void createPresenter() {
        BlogInfoPresenter.createPresenter(this);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        presenterWriterInfoContractor.setBlogInfo((BlogInfo) Parcels.unwrap(savedInstanceState.getParcelable(KEY_BLOG_INFO)));
    }

    private void restoreView() {
    }

    private void restoreArgument(Bundle bundle) {

    }

    private void initialize() {
        presenterWriterInfoContractor.loadBlogInfo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_BLOG_INFO, Parcels.wrap(presenterWriterInfoContractor.getBlogInfo()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterWriterInfoContractor.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenterWriterInfoContractor.stop();
    }

    @Override
    public void setPresenter(BlogInfoContractor.Presenter presenter) {
        this.presenterWriterInfoContractor = presenter;
    }

    @Override
    public void setBlogId(String id) {
        tvBlogId.setText(id);
    }

    @Override
    public void setBlogName(String name) {
        tvBlogName.setText(name);
    }

    @Override
    public void setBlogDescription(String description) {
        tvBlogDescription.setText(description);
    }

    @Override
    public void setBlogUrl(String url) {
        tvBlogUrl.setText(url);
    }

    @Override
    public void setBlogPostCount(String postCount) {
        tvBlogPostCount.setText(postCount);
    }

    @Override
    public void showPostListLoadingFailure() {
        Toast.makeText(this, R.string.service_unavailable, Toast.LENGTH_SHORT).show();
    }
}
