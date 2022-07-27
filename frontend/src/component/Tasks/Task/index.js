import React from 'react'
import { Link } from 'react-router-dom';

export default function Task(props) {
    const {task} = props;
  return (
    <div>
        <h1>
        <Link to={"/marker/"+task.id}>{task.id}</Link>         
        </h1>
        <h1>{task.input}</h1>    
    </div>
  )
}
