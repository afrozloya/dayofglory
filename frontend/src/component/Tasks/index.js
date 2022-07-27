import { MDBRow } from 'mdb-react-ui-kit';
import React, { useEffect, useState } from 'react'
import { LIST_TASK_URL } from '../../constants/AppConstants';
import Task from './Task';
import axios from 'axios';

export default function Tasks() {    
    const [taskList, setTaskList] = useState([]);

    useEffect(() => {
        axios.get(LIST_TASK_URL).then((response) => {
            setTaskList(response.data)
        });
    }, [])
    
    
  return (
    <>
        <h1>index</h1>
        <MDBRow>
        {taskList?.map((task) => (
            <Task task={task} key={task.id} />
        ))}
        </MDBRow>        
    </>
  )
}
