package com.nurdcoder.android.icr_wallet.data.remote.helper.callback;

import java.util.HashMap;
import java.util.Map;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [17-Jul-2018 at 11:21 AM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: Generic API.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [17-Jul-2018 at 11:21 AM].
 * * --> <Second Editor> on [17-Jul-2018 at 11:21 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [17-Jul-2018 at 11:21 AM].
 * * --> <Second Reviewer> on [17-Jul-2018 at 11:21 AM].
 * * ============================================================================
 **/


public interface FileTransferCallback {
    void onFileUploadSuccess(String filePath);

    void onFileUploadProgress(String filePath, int percentage);

    void onFileUploadFailed(String filePath, int reason);

    /*
    void onStateChanged(int id, TransferState state);

    void onProgressChanged(int id, long bytesCurrent, long bytesTotal);

    void onError(int id, Exception ex);
    */

    enum TransferState {

        /**
         * This state represents a transfer that has been queued, but has not yet
         * started
         */
        WAITING,
        /**
         * This state represents a transfer that is currently uploading or
         * downloading data
         */
        IN_PROGRESS,
        /**
         * This state represents a transfer that is paused
         */
        PAUSED,
        /**
         * This state represents a transfer that has been resumed and queued for
         * execution, but has not started to actively transfer data
         */
        RESUMED_WAITING,
        /**
         * This state represents a transfer that is completed
         */
        COMPLETED,
        /**
         * This state represents a transfer that is canceled
         */
        CANCELED,
        /**
         * This state represents a transfer that has failed
         */
        FAILED,

        /**
         * This state represents a transfer that is currently on hold, waiting for
         * the network to become available
         */
        WAITING_FOR_NETWORK,
        /**
         * This state represents a transfer that is a completed part of a multi-part
         * upload. This state is primarily used internally and there should be no
         * need to use this state.
         */
        PART_COMPLETED,
        /**
         * This state represents a transfer that has been requested to cancel, but
         * the service processing transfers has not yet fulfilled this request. This
         * state is primarily used internally and there should be no need to use
         * this state.
         */
        PENDING_CANCEL,
        /**
         * This state represents a transfer that has been requested to pause by the
         * client, but the service processing transfers has not yet fulfilled this
         * request. This state is primarily used internally and there should be no
         * need to use this state.
         */
        PENDING_PAUSE,
        /**
         * This state represents a transfer that has been requested to pause by the
         * client because the network has been loss, but the service processing
         * transfers has not yet fulfilled this request. This state is primarily
         * used internally and there should be no need to use this state.
         */
        PENDING_NETWORK_DISCONNECT,
        /**
         * This is an internal value used to detect if the current transfer is in an
         * unknown state
         */
        UNKNOWN;

        private static final Map<String, TransferState> MAP;

        static {
            MAP = new HashMap<String, TransferState>();
            for (final TransferState state : TransferState.values()) {
                MAP.put(state.toString(), state);
            }
        }


        /**
         * Returns the transfer state from string
         *
         * @param stateAsString state of the transfer represented as string.
         * @return the {@link TransferState}
         */
        public static TransferState getState(String stateAsString) {
            if (MAP.containsKey(stateAsString)) {
                return MAP.get(stateAsString);
            }

            return UNKNOWN;
        }
    }
}