import React, { Suspense } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AnnotationMarker from '../component/AnnotationMarker';
import Tasks from '../component/Tasks';
import Loader from '../component/Loader';

const AppRoutes = () => (
  <>
    <BrowserRouter>
      <Suspense fallback={<Loader />}>
          <Routes>
            <Route exact path="/" element={<Tasks />} />
            <Route exact path="/marker/:id" element={<AnnotationMarker />} />
          </Routes>
      </Suspense>
    </BrowserRouter>
  </>
);
export default AppRoutes;
