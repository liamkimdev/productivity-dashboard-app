import { createSlice } from '@reduxjs/toolkit';

export const messagesSlice = createSlice({
  name: 'messages',

  initialState: {
    messageId: '',
    message: '',
  },

  reducers: {
    setMessages: (state, action) => {
      state.messageId = action.payload.messageId;
      state.message = action.payload.message;
    },

    clearMessages: (state) => {
      state.messageId = '';
      state.message = '';
    },
  },
});

export const { setMessages, clearMessages } = messagesSlice.actions;
export default messagesSlice.reducer;
