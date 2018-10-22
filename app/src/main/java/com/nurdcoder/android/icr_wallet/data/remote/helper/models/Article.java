package com.nurdcoder.android.icr_wallet.data.remote.helper.models;

import java.util.List;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [19-Jul-2018 at 4:24 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [19-Jul-2018 at 4:24 PM].
 * * --> <Second Editor> on [19-Jul-2018 at 4:24 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [19-Jul-2018 at 4:24 PM].
 * * --> <Second Reviewer> on [19-Jul-2018 at 4:24 PM].
 * * ============================================================================
 **/

public class Article {
    public String title;
    public String description;
    public String dateTime;
    public String location;

    public Reaction mReaction;

    public List<ArticleComment> mArticleComment;
}
