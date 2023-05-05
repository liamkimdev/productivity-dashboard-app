import { createSlice } from '@reduxjs/toolkit';

export const noteSlice = createSlice({
  name: 'note',

  initialState: {
    notes: [],
    // noteId: '',
    // noteTitle: '',
    // noteDescription: '',
    // noteDate: '',
    // noteDashboardId: '',
  },

  reducers: {
    setNotes: (state, action) => {
      state.notes = action.payload;
    },

    createNote: (state, action) => {
      state.notes.push(action.payload);
    },

    findNoteById: (state, action) => {
      const id = action.payload;
      const note = state.notes.find((note) => note.id === id);
    },

    findNoteByDate: (state, action) => {},
    findNoteByDescription: (state, action) => {},

    deleteNoteById: (state, action) => {
      const id = action.payload;
      state.notes = state.notes.filter((note) => note.id !== id);
    },

    deleteAllNotes: (state) => {
      state.notes = [];
    },
  },
});

export const {
  setNotes,
  createNote,
  findNoteById,
  findNoteByDate,
  findNoteByDescription,
  deleteNoteById,
  deleteAllNotes,
} = noteSlice.actions;
export default noteSlice.reducer;
