import { createSlice } from '@reduxjs/toolkit';

export const messagesSlice = createSlice({
  name: 'messages',

  initialState: {
    messageId: '',
    messageType: '',
    message: '',
  },

  reducers: {
    setMessages: (state, action) => {
      state.messageId = action.payload.messageId;
      state.messageType = action.payload.messageType;
      state.message = action.payload.message;
    },

    clearMessages: (state) => {
      state.messageId = '';
      state.messageType = '';
      state.message = '';
    },
  },
});

export const { setMessages, clearMessages } = messagesSlice.actions;
export default messagesSlice.reducer;
