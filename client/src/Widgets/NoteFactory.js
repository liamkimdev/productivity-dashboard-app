import react, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { setNotes } from '../store/slices/NoteSlice';
import { setMessages } from '../store/slices/MessagesSlice';

function NoteFactory() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const authToken = useSelector((state) => state.auth.authToken);
  const notes = useSelector((state) => state.note.notes);

  useEffect(() => {
    getNotes();
  }, []);
  //the above use effect will call defined functions and the empty [] tells react to call it only once, if we put state names in the array it would indicate to load everytime that state changes

  const getNotes = () => {
    console.log(authToken);
    console.log(notes);

    fetch('http://localhost:8080/dashboard/noteWidget/note', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + authToken,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        dispatch(setNotes(data));
        console.log(notes);
      });
  };

  // const editNote = (pokemon) => {
  //   setCurrentPokemon(pokemon);
  //   navigate('/form');
  // };

  // const deleteNote = (pokemon) => {
  //   setCurrentPokemon(pokemon);
  //   navigate('/confirm-delete');
  // };

  const CreateNoteFactory = () => {
    if (notes.length > 0) {
      let noteCardArray = notes.map((noteObj) => {
        return (
          // <Card key={noteObj.noteId + '-' + noteObj.noteDate} note={noteObj} />
          <Card style={{ width: '18rem' }}>
            <Card.Body>
              <Card.Title>${noteObj.title}</Card.Title>
              <Card.Text>${noteObj.description}</Card.Text>

              <Button variant="primary">Edit</Button>
              <Button variant="primary">Delete</Button>
            </Card.Body>
          </Card>
        );
      });
      console.log(noteCardArray);
      return noteCardArray;
    }
  };

  return CreateNoteFactory();
}

export default NoteFactory;
