import { Outlet, Link } from "react-router-dom";
import React from "react";
import Footer from "./Footer";

function Layout() {
    return (
        <div>
            {/* Navigation bar */}
            <nav>
                <Link to="/">Home</Link>
                <span> | </span>
                <Link to="/highcharts-examples">Highcharts examples</Link>
                <span> | </span>
                <Link to="/contact">Contact</Link>
            </nav>
            {/* Outlet is the space where the pages are rendered*/}
            <Outlet />
            {/* Footer goes here */}
            <Footer />
        </div>
    );
};

export default Layout;