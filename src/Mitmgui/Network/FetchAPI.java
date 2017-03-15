package Mitmgui.Network;

import Mitmgui.Models.Flows.FlowModel;

/**
 * Created by teixeiras on 12/03/2017.
 */
public class FetchAPI {

    public void resume(FlowModel flow) {
        return dispatch => fetchApi(`/flows/${flow.id}/resume`, { method: 'POST' })
    }

    public void resumeAll() {
        return dispatch => fetchApi('/flows/resume', { method: 'POST' })
    }

    public void  kill(FlowModel flow) {
        return dispatch => fetchApi(`/flows/${flow.id}/kill`, { method: 'POST' })
    }

    public void  killAll() {
        return dispatch => fetchApi('/flows/kill', { method: 'POST' })
    }


    public void  remove(FlowModel flow) {
        return dispatch => fetchApi(`/flows/${flow.id}`, { method: 'DELETE' })
    }

    public void duplicate(FlowModel flow) {
        return dispatch => fetchApi(`/flows/${flow.id}/duplicate`, { method: 'POST' })
    }

    public void replay(FlowModel flow) {
        return dispatch => fetchApi(`/flows/${flow.id}/replay`, { method: 'POST' })
    }

    public void  revert(FlowModel flow) {
        return dispatch => fetchApi(`/flows/${flow.id}/revert`, { method: 'POST' })
    }

    public void  update(FlowModel flow, data) {
        return dispatch => fetchApi.put(`/flows/${flow.id}`, data)
    }

    public void  uploadContent(FlowModel flow, file, type) {
    const body = new FormData()
        file       = new window.Blob([file], { type: 'plain/text' })
        body.append('file', file)
        return dispatch => fetchApi(`/flows/${flow.id}/${type}/content`, { method: 'post', body })
    }


    public void  clear() {
        return dispatch => fetchApi('/clear', { method: 'POST' })
    }

    public void  download() {
        window.location = '/flows/dump'
        return { type: REQUEST_ACTION }
    }

    public void  upload(file) {
    const body = new FormData()
        body.append('file', file)
        return dispatch => fetchApi('/flows/dump', { method: 'post', body })
    }

}
