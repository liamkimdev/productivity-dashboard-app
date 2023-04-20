import { useEffect } from 'react';

// Redux
import { useSelector, useDispatch } from 'react-redux';
import { clearMessages } from '../store/slices/MessagesSlice';

function Message() {
  const dispatch = useDispatch();

  //Redux's state.
  const message = useSelector((state) => state.messages.message);
  const messageId = useSelector((state) => state.messages.messageId);
  const messageType = useSelector((state) => state.messages.messageType);
  // console.log(messageType);

  // const handleClose = () => {
  //   // let filteredMessages = messages.filter((m) => m.id !== message.id);
  //   // setMessages(filteredMessages);

  //   dispatch(clearMessages());
  // };

  useEffect(() => {
    const timer = setTimeout(() => {
      dispatch(clearMessages());
    }, 5000);

    return () => {
      clearTimeout(timer);
    };
  }, []);

  const getClassNameBasedOnType = (messageType) => {
    if (messageType === 'success') {
      return 'alert alert-success';
    } else if (messageType === 'failure') {
      return 'alert alert-danger';
    } else {
      return ''; // Set a default class for blank messageType
    }
  };

  return (
    <div className={getClassNameBasedOnType(messageType)}>
      <div className="row">
        <div className="col-11">
          <p className="mb-0">{message}</p>
        </div>
        <div className="col-1 text-end">
          {/* <button
            type="button"
            className="btn-close"
            aria-label="Close"
            // onClick={handleClose}
          ></button> */}
        </div>
      </div>
    </div>
  );
}

export default Message;
