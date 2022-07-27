import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom';
import { LIST_TASK_URL } from '../../constants/AppConstants';
import Annotation from 'react-image-annotation'
import { MDBBtn } from 'mdb-react-ui-kit';

export default function AnnotationMarker() {
  const [annotation, setAnnotation] = useState({})
  const [annotations, setAnnotations] = useState([])
  const { id } = useParams();
  const [imageUrl, setImageUrl] = useState("");

  useEffect(() => {
    axios.get(LIST_TASK_URL+"/"+id).then((response) => {
      console.log(response.data.input);
      setImageUrl(response.data.input)
  });    
  }, [id])

  const navigate = useNavigate();


  const onUpdate = () => {
    const newAnnot = annotations.map(a => ({...a, tag:a.data.text}))
    const task = {anotations:newAnnot}
    console.log(task)
    axios.put(LIST_TASK_URL+"/"+id, task).then((response) => {
      console.log(response.data.input);
      navigate("/")
    });    
  }

  

  const onChange = (annotation) => {
    setAnnotation(annotation)
  }
  const onSubmit = (annotation) => {
    const { geometry, data } = annotation
    setAnnotation({})
    setAnnotations(
      annotations.concat({
        geometry,
        data: {
          ...data,
        }
      })
    );
  }
  return (
    <>
    <div>{id}</div>
    <MDBBtn onClick={onUpdate} color="primary" className='my-3'>Update</MDBBtn>  
    <Annotation
        src={imageUrl}
        annotations={annotations}
        value={annotation}
        onChange={onChange}
        onSubmit={onSubmit}
        allowTouch
      />
    </> 
  )
}
